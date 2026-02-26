const ShadowCore = (function () {

    // ================= CONFIG =================
    const AUTO_DESTROY_MS = 20000;
    const MAX_RUN_CALLS = 5;

    // ================= PRIVATE STATE =================
    let _alive = true;
    let _runCount = 0;
    let _memory = new Map();
    let _destroyTimer;

    const _secretKey = crypto.getRandomValues(new Uint32Array(4)).join("-");
    const _encoder = new TextEncoder();
    const _decoder = new TextDecoder();

    // ================= UTIL =================
    function _panic(msg) {
        console.error("ShadowCore:", msg);
        _destroy(true);
        throw new Error(msg);
    }

    function _destroy(force = false) {
        if (!_alive && !force) return;

        _alive = false;
        clearTimeout(_destroyTimer);

        // wipe memory
        _memory.forEach((v, k) => {
            _memory.set(k, null);
        });
        _memory.clear();
    }

    function _startTimer() {
        _destroyTimer = setTimeout(() => {
            console.warn("ShadowCore auto-destroyed.");
            _destroy();
        }, AUTO_DESTROY_MS);
    }

    function _checkAlive() {
        if (!_alive) return false;
        return true;
    }

    // ================= ENCRYPTION =================
    async function _generateKey() {
        return crypto.subtle.generateKey(
            { name: "AES-GCM", length: 256 },
            true,
            ["encrypt", "decrypt"]
        );
    }

    let _cryptoKeyPromise = _generateKey();

    async function _encrypt(data) {
        const key = await _cryptoKeyPromise;
        const iv = crypto.getRandomValues(new Uint8Array(12));
        const encrypted = await crypto.subtle.encrypt(
            { name: "AES-GCM", iv },
            key,
            _encoder.encode(JSON.stringify(data))
        );
        return { iv: Array.from(iv), data: Array.from(new Uint8Array(encrypted)) };
    }

    async function _decrypt(payload) {
        const key = await _cryptoKeyPromise;
        const decrypted = await crypto.subtle.decrypt(
            { name: "AES-GCM", iv: new Uint8Array(payload.iv) },
            key,
            new Uint8Array(payload.data)
        );
        return JSON.parse(_decoder.decode(decrypted));
    }

    // ================= SAFE MATH RUNNER =================
    function _safeRun(expression) {
        if (_runCount >= MAX_RUN_CALLS) {
            _panic("Run limit exceeded");
        }

        if (!/^[0-9+\-*/().\s]+$/.test(expression)) {
            _panic("Unsafe expression blocked");
        }

        _runCount++;
        return Function(`"use strict"; return (${expression})`)();
    }

    // ================= API =================
    const api = {

        async store(key, value, ttl = 10000) {
            if (!_checkAlive()) return "Core destroyed";

            const encrypted = await _encrypt(value);

            _memory.set(key, {
                payload: encrypted,
                expires: Date.now() + ttl
            });

            return "Stored securely";
        },

        async read(key) {
            if (!_checkAlive()) return "Core destroyed";

            const record = _memory.get(key);
            if (!record) return null;

            if (Date.now() > record.expires) {
                _memory.delete(key);
                return "Expired";
            }

            return await _decrypt(record.payload);
        },

        run(expression) {
            if (!_checkAlive()) return "Core destroyed";
            return _safeRun(expression);
        },

        reveal(secret) {
            if (!_checkAlive()) return "Core destroyed";

            if (secret === _secretKey) {
                return {
                    alive: _alive,
                    storedKeys: _memory.size,
                    remainingRuns: MAX_RUN_CALLS - _runCount
                };
            }

            return "Access denied";
        },

        destroy() {
            _destroy();
            return "Core manually destroyed";
        }
    };

    Object.freeze(api);
    _startTimer();

    return new Proxy(api, {
        get(target, prop) {
            if (!_alive) return () => "Core destroyed";
            if (!(prop in target)) {
                return () => "Unknown command";
            }
            return target[prop];
        }
    });

})();
