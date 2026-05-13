# File: async_log_analyzer.py

import asyncio, aiofiles, re
from collections import Counter

async def analyze(file):
    counter = Counter()
    async with aiofiles.open(file, "r") as f:
        async for line in f:
            ips = re.findall(r"\b(?:\d{1,3}\.){3}\d{1,3}\b", line)
            counter.update(ips)
    return counter.most_common(5)

async def main():
    result = await analyze("server.log")
    for ip, count in result:
        print(f"{ip} -> {count} requests")

if __name__ == "__main__":
    asyncio.run(main())
