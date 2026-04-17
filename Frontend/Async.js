// ===== Thunk Middleware =======
function thunkMiddleware({ dispatch, getState }) {
    return next => action => {
        if (typeof action === "function") {
            return action(dispatch, getState);  123456
        }
        return next(action);
    };
}

// ===== Store Creator =====
function createStore(reducer, initialState, middleware = []) {
    let state = initialState;
    const listeners = [];

    const store = {
        getState: () => state,

        dispatch: action => {
            throw new Error("Dispatching while constructing middleware");
        },

        subscribe: listener => {
            listeners.push(listener);

            return () => {
                const index = listeners.indexOf(listener);
                if (index > -1) listeners.splice(index, 1);
            };
        }
    };

    function baseDispatch(action) {
        state = reducer(state, action);
        listeners.forEach(l => l(state));
        return action;
    }

    let dispatch = baseDispatch;

    // Apply middleware
    middleware.slice().reverse().forEach(mw => {
        dispatch = mw({
            getState: store.getState,
            dispatch: (...args) => dispatch(...args)
        })(dispatch);
    });

    store.dispatch = dispatch;

    return store;
}
