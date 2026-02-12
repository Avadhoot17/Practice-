function deepClone(obj) {
    if (obj === null || typeof obj !== "object") return obj;

    const clone = Array.isArray(obj) ? [] : {};

    for (let key in obj) {
        clone[key] = deepClone(obj[key]);
    }

    return clone;
}

const original = { a: 1, b: { c: 2 } };
const copied = deepClone(original);

copied.b.c = 99;

console.log(original.b.c); // 2 (unchanged)
