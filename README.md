# Compare Currency Rates Service

The service compares the exchange rates for the yesterday and the today dates and returns gif as a result. 
The service uses external resources:
  - https://openexchangerates.org
  - https://api.giphy.com
 
### Service API, URL format
The API base path is http://localhost:8080/rates/
API endpoints (currency code*) are then appended to this base path, like so:
```sh
$ http://localhost:8080/rates/USD
$ http://localhost:8080/rates/EUR
```
**you can get correct currency codes from https://openexchangerates.org*

### Service API response
Service return view. View depends on the comparison result: default gif with tag "broke" will be returned if yesterday's exchange rate is higher than the current one, conversely - gif with tag "rich", if equals - gif with tag "laughing".

### Application-properties
You can manually configure some application-properties.
| Property | Description |
| ------ | ------ |
| openexchangerates.app_id | used to connect to https://openexchangerates.org|
| openexchangerates.base | used to set base currency, default - RUB|
| giphy.api_key | used to connect to https://api.giphy.com|
| giphy.tag.negative | tag used if yesterday's exchange rate is higher than the current one|
| giphy.tag.positive | tag used if today's exchange rate is higher than the yesterday's|
| giphy.tag.neutral | tag used when exchange rates are equals for today and yesterday|

### Для запуска приложения в Docker
В корневой папке приложения выполнить команды:
1. docker build -t currency .
2. docker run -p 8080:8080 currency
