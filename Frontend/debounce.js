function debounce(fn, delay, options = {}) {
    let timer = null;
    let lastArgs;
    let lastContext;
    let result;

    const { leading = false, trailing = true } = options;

    function invoke() {
        result = fn.apply(lastContext, lastArgs);
        lastArgs = lastContext = null;
    }

    const debounced = function (...args) {
        lastArgs = args;
        lastContext = this;

        const shouldCallNow = leading && !timer;

        clearTimeout(timer);

        timer = setTimeout(() => {
            timer = null;
            if (trailing && lastArgs) invoke();
        }, delay);

        if (shouldCallNow) invoke();

        return result;
    };

    // Cancel pending execution
    debounced.cancel = function () {
        clearTimeout(timer);
        timer = null;
        lastArgs = lastContext = null;
    };

    // Execute immediately if waiting
    debounced.flush = function () {
        if (timer) {
            clearTimeout(timer);
            invoke();
            timer = null;
        }
    };

    return debounced;
}
