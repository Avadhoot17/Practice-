const SecretVault = (function () {

    // ===== Truly private memory (closure protected) =====
    const memory = new Map();
    const masterKey = Math.random().toString(36).slice(2);

    // ===== Internal validator =====
    function isValid(key) {
        return key === masterKey;
    }

    // ===== Internal audit trail =====
    const history = [];

    function log(action) {
        history.push({
            action,
            time: new Date().toISOString()
        });
    }

    // ===== Public secure API =====
    return {

        // Store secret value
        set(key, name, value) {
            if (!isValid(key)) return "Invalid key";

            memory.set(name, JSON.stringify(value));
            log(`SET → ${name}`);
            return "Saved";
        },

        // Read secret value
        get(key, name) {
            if (!isValid(key)) return "Invalid key";

            const data = memory.get(name);
            log(`GET → ${name}`);
            return data ? JSON.parse(data) : null;
        },

        // Delete value
        remove(key, name) {
            if (!isValid(key)) return "Invalid key";

            memory.delete(name);
            log(`REMOVE → ${name}`);
            return "Deleted";
        },

        // Hidden debug access (still protected by closure)
        audit(key) {
            if (!isValid(key)) return "Access denied";
            return [...history];
        },

        // Trick to reveal master key indirectly
        _hint() {
            return masterKey.split("").reverse().join("");
        }
    };

})();
