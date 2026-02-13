const SecureMemoryCore = (function () {

    // ===== Completely private memory =====
    const users = new Map();        // username -> encrypted data
    const sessions = new Map();     // token -> { user, expiry }
    const logs = [];

    const SECRET_SALT = Math.random().toString(36).slice(2);

    // ===== Utility functions (hidden) =====
    const hash = str =>
        [...(str + SECRET_SALT)]
            .reduce((a, c) => a + c.charCodeAt(0), 0)
            .toString(36);

    const now = () => Date.now();

    const log = msg => {
        logs.push({ msg, time: new Date().toISOString() });
    };

    function validateSession(token) {
        const session = sessions.get(token);
        if (!session) return null;

        if (session.expiry < now()) {
            sessions.delete(token);
            log("Session expired");
            return null;
        }

        return session.user;
    }

    // ===== Public API =====
    return Object.freeze({

        // Register new user
        register(username, password) {
            if (users.has(username)) return "User exists";

            users.set(username, hash(password));
            log(`Registered: ${username}`);
            return "Registered";
        },

        // Login → returns hidden token
        login(username, password) {
            const stored = users.get(username);
            if (!stored || stored !== hash(password)) {
                log(`Failed login: ${username}`);
                return null;
            }

            const token = hash(username + now() + Math.random());
            sessions.set(token, {
                user: username,
                expiry: now() + 10000 // 10 sec session
            });

            log(`Login success: ${username}`);
            return token;
        },

        // Store secret data per user
        store(token, key, value) {
            const user = validateSession(token);
            if (!user) return "Unauthorized";

            if (!users.has(user + "_data")) {
                users.set(user + "_data", new Map());
            }

            users.get(user + "_data").set(key, JSON.stringify(value));
            log(`Stored ${key} for ${user}`);
            return "Saved";
        },

        // Read secret data
        read(token, key) {
            const user = validateSession(token);
            if (!user) return "Unauthorized";

            const dataMap = users.get(user + "_data");
            if (!dataMap) return null;

            const val = dataMap.get(key);
            log(`Read ${key} for ${user}`);
            return val ? JSON.parse(val) : null;
        },

        // Hidden audit (still closure-protected)
        _audit(secret) {
            if (secret !== SECRET_SALT) return "Denied";
            return [...logs];
        }

    });

})();
