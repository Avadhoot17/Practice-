function fakeApiCall(success = true) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            success ? resolve("Data received") : reject("API failed");
        }, 1000);
    });
}

async function loadData() {
    try {
        const result = await fakeApiCall(true);
        console.log(result);
    } catch (error) {
        console.error("Error:", error);
    }
}

loadData();
