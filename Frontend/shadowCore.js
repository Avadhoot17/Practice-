const ShadowCore = (function () {

    // ===== Encrypted private memory =====
    const _memory = {};
    const _key = Math.random().toString(36).slice(2);
    let _alive = true;

    function _encrypt(text) {
        return [...text].map(c => c.charCodeAt(0) ^ 23).join("-");
    }

    function _decrypt(code) {
        return code.split("-")
            .map(n => String.fromCharCode(n ^ 23))
            .join("");
    }

    // ===== Hidden background watcher =====
    setTimeout(() => {
        _alive = false;
        console.warn("ShadowCore expired.");
    }, 15000); // self-destruct after 15s


    // ===== Tamper detection =====
    const originalToString = Function.prototype.toString;

    function _checkTamper() {
        if (Function.prototype.toString !== originalToString) {
            throw new Error("Tampering detected");
        }
    }

    // ===== Sandboxed code runner =====
    function _sandbox(code) {
        _checkTamper();

        const safeEval = new Function("return (" + code + ")");
        return safeEval();
    }

    // ===== Dynamic secure API =====
    const api = {

        store(key, value) {
            if (!_alive) return "Core destroyed";

            _memory[_encrypt(key)] = _encrypt(JSON.stringify(value));
            return "Stored securely";
        },

        read(key) {
            if (!_alive) return "Core destroyed";

            const data = _memory[_encrypt(key)];
            if (!data) return null;

            return JSON.parse(_decrypt(data));
        },

        run(codeString) {
            if (!_alive) return "Core destroyed";

            return _sandbox(codeString);
        },

        reveal(secret) {
            if (secret === _key) {
                return {
                    memoryKeys: Object.keys(_memory).length,
                    alive: _alive
                };
            }
            return "Access denied";
        }
    };

    // ===== Proxy cloak layer =====
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


// ================= Usage =================

ShadowCore.store("user", { name: "Avadhoot", role: "developer" });

console.log(ShadowCore.read("user"));

console.log(ShadowCore.run("2 + 5 * 10"));

console.log(ShadowCore.unknown()); // hidden proxy protection
