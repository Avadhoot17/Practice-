const ClosureThunkStore = (function () {

    // ===== Private closure state =====
    let state = {};
    let reducer = null;

    const listeners = new Set();
    const middlewares = [];

    const clone = obj => JSON.parse(JSON.stringify(obj));

    // ===== Core dispatch (no middleware) =====
    function baseDispatch(action) {
        const newState = reducer(clone(state), action);

        if (newState === undefined) {
            throw new Error("Reducer must return state");
        }

        state = clone(newState);
        listeners.forEach(fn => fn(clone(state)));
        return action;
    }

    // ===== Build middleware chain =====
    function applyMiddleware() {
        let dispatch = baseDispatch;

        const middlewareAPI = {
            getState: () => clone(state),
            dispatch: action => dispatch(action)
        };

        // compose middlewares right → left
        const chain = middlewares.map(mw => mw(middlewareAPI));
        dispatch = chain.reduceRight((next, mw) => mw(next), baseDispatch);

        return dispatch;
    }

    let enhancedDispatch = baseDispatch;

    // ===== Public API =====
    return {

        create(rootReducer, initialState = {}) {
            reducer = rootReducer;
            state = clone(initialState);
            enhancedDispatch = applyMiddleware();
        },

        dispatch(action) {
            return enhancedDispatch(action);
        },

        getState() {
            return clone(state);
        },

        subscribe(fn) {
            listeners.add(fn);
            return () => listeners.delete(fn);
        },

        use(middleware) {
            middlewares.push(middleware);
            enhancedDispatch = applyMiddleware();
        }
    };

})();
