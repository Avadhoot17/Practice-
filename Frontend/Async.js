// ===== Reducer =====
function counterReducer(state, action) {
    switch (action.type) {
        case "INC":
            return { ...state, count: state.count + 1 };

        case "SET":
            return { ...state, count: action.payload };

        default:
            return state;
    }
}

// ===== Create Store =====
ClosureThunkStore.use(thunkMiddleware);
ClosureThunkStore.create(counterReducer, { count: 0 });

// ===== Subscribe =====
ClosureThunkStore.subscribe(s => console.log("State:", s));

// ===== Normal dispatch =====
ClosureThunkStore.dispatch({ type: "INC" });

// ===== Async dispatch using thunk =====
ClosureThunkStore.dispatch((dispatch, getState) => {
    console.log("Before async:", getState());

    setTimeout(() => {
        dispatch({ type: "SET", payload: 10 });
        console.log("After async:", getState());
    }, 1000);
});
