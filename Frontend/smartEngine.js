// Smart Engine with hidden internal behavior
const SmartEngine = (function () {

    // ===== Private hidden state =====
    let secretKey = Math.random().toString(36).slice(2);
    let logs = [];
    let debug = false;

    // ===== Hidden logger =====
    function log(message) {
        logs.push({
            time: new Date().toISOString(),
            message
        });

        if (debug) console.log("[DEBUG]", message);
    }

    // ===== Lazy execution cache =====
    const cache = new Map();

    function heavyComputation(n) {
        if (cache.has(n)) return cache.get(n);

        log(`Computing value for ${n}`);

        let result = 0;
        for (let i = 0; i < 1e6; i++) {
            result += (n * i) % 7;
        }

        cache.set(n, result);
        return result;
    }

    // ===== Proxy to intercept method calls =====
    const api = {
        compute(n) {
            return heavyComputation(n);
        },

        enableDebug(key) {
            if (key === secretKey) {
                debug = true;
                return "Debug mode ON";
            }
            return "Access denied";
        },

        getLogs(key) {
            if (key === secretKey) return logs;
            return "Unauthorized";
        }
    };

    // ===== Self-modifying protection =====
    let accessCount = 0;

    function protect() {
        accessCount++;

        if (accessCount > 5) {
            log("Too many accesses — locking engine");
            return "Engine locked";
        }

        return new Proxy(api, {
            get(target, prop) {
                log(`Method accessed: ${prop}`);
                return target[prop];
            }
        });
    }

    // ===== Public interface =====
    return {
        access() {
            return protect();
        },

        // hidden way to reveal key (not obvious)
        _revealKey() {
            return secretKey.split("").reverse().join("");
        }
    };

})();


// ================= Usage =================

const engine = SmartEngine.access();

console.log(engine.compute(10));
console.log(engine.compute(10)); // comes from cache

// Hidden trick to get secret key
const hiddenKey = SmartEngine._revealKey().split("").reverse().join("");

console.log(engine.enableDebug(hiddenKey));
console.log(engine.getLogs(hiddenKey));
