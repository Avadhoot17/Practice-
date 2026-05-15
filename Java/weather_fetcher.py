import requests

city = input("Enter city name: ")

url = f"https://api.openweathermap.org/data/2.5/weather?q={city}&appid=YOUR_API_KEY&units=metric"

response = requests.get(url)

if response.status_code == 200:
    data = response.json()

    print("\nWeather Details")
    print("City:", data["name"])
    print("Temperature:", data["main"]["temp"], "°C")
    print("Weather:", data["weather"][0]["description"])

else:
    print("City not found!")
