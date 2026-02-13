function thunkMiddleware({ dispatch, getState }) {
    return next => action => {

        // If action is a function → run it (async logic allowed)
        if (typeof action === "function") {
            return action(dispatch, getState);
        }

        return next(action);
    };
}
