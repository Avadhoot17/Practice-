function debounce(fn, delay) {
    let timer;

    return function (...args) {
        clearTimeout(timer);
        timer = setTimeout(() => fn.apply(this, args), delay);
    };
}

function search(query) {
    console.log("Searching for:", query);
}

const debouncedSearch = debounce(search, 500);

// Simulating fast typing
debouncedSearch("J");
debouncedSearch("Ja");
debouncedSearch("Jav");
debouncedSearch("JavaScript");
