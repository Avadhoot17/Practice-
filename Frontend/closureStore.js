const ClosureStore = (function () {

    // ===== Private state inside closure =====
    let state = {};
    let reducer = null;

    const listeners = new Set();
    const history = [];

    // ===== Internal immutable clone =====
    const clone = obj => JSON.parse(JSON.stringify(obj));

    // ===== Notify subscribers =====
    function notify() {
        listeners.forEach(fn => fn(clone(state)));
    }

    // ===== Public API =====
    return {

        // Initialize store with reducer + initial state
        create(rootReducer, initialState = {}) {
            reducer = rootReducer;
            state = clone(initialState);
            history.push(clone(state));
        },

        // Get current state (read-only copy)
        getState() {
            return clone(state);
        },

        // Dispatch action → reducer handles update
        dispatch(action) {
            if (typeof reducer !== "function") {
                throw new Error("Reducer not initialized");
            }

            const newState = reducer(clone(state), action);

            // prevent mutation-based reducers
            if (newState === undefined) {
                throw new Error("Reducer must return new state");
            }

            state = clone(newState);
            history.push(clone(state));

            notify();
        },

        // Subscribe to state changes
        subscribe(fn) {
            listeners.add(fn);
            return () => listeners.delete(fn); // unsubscribe
        },

        // Undo last state (time travel)
        undo() {
            if (history.length <= 1) return;

            history.pop();
            state = clone(history[history.length - 1]);
            notify();
        },

        // Hidden debug history access
        _history(secret) {
            if (secret !== "dev") return "Access denied";
            return clone(history);
        }

    };

})();
