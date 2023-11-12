# AnimeSearchUS API

Custom API made for [animesearch.us](https://animesearch.us). This API is meant to be a private, extended, improved, version of the Kitsu anime API. 

## Table of Contents

<div id="user-content-toc">
  <ul>
    <li><a href="#purpose">Purpose</a></li>
    <li><a href="#tools-and-technologies">Tools and Technologies</a></li>
    <li><a href="#endpoints">Endpoints</a></li>
    <li><a href="#disclaimer">Disclaimer</a></li>
  </ul>
</div>

## Purpose

As part of the development of [animesearch.us](https://animesearch.us), a need for user authorization and better filtering surged. 

Core issues with [animesearch.us](https://animesearch.us) as of **September 3, 2023**:

1. Filtering by dates is tricky with the Kitsu API.
    - Even though the Kitsu API does provide a relatively decent filtering system. When filtering by dates, the anime do not appear in the correct order, and it gets messed up. This can be fixed on the front-end up to a certain extent. 
2. Performance
    - Kitsu API provides great performance; however, being a public API makes it more susceptible to downtimes, periods of slow performance, malicious attacks, etc. All these are issues that are out of my control.
3. Data Accuracy
    - All data that is inaccurate cannot be fixed since AnimeSearchUS uses a third party API (Kitsu). These inaccurate data is seen most often in dates, genres, and sometimes media type. 
4. Missing Data
    - The front-end needs to do a lot of conditional rendering because the Kitsu API is not always consistent with the data it provides across the board. For example, some anime may be missing their English title or their romaji title. Some may not even have an episode count, or age rating.

This API is aimed to provided better service to animesearch.us. A better service indicating higher availability, faster queries, a more extensive, accurate, and well documented filtering, in-house user auth, and better insights into user trends.

## Tools and Technologies

## Endpoints

## Disclaimer
​
When the terms "I," "me," "my," or "developer" are used within this document or repository, they refer explicitly to Ernesto Gonzalez, the developer of the Animesearch.us API.
​
### Permitted Use of API Endpoints
​
Although this repository is private, the public API endpoints are accessible. You are welcome to make use of these public endpoints but are asked to adhere to the following conditions:
​
1.  **Rate Limiting**: Please respect any rate limits that may be in place.
2.  **Data Usage**: If you use the API to gather data, make sure to respect copyright laws and the terms of service of any third-party services integrated within the API.
3.  **Crediting**: While not mandatory, a mention of Animesearch.us as the source of your data would be appreciated.
4.  **No Abuse**: The API is intended for genuine usage scenarios. Any form of abuse, including but not limited to scraping, data harvesting, or the use of the API for unauthorized commercial activities is strictly prohibited.
5.  **No Warranty**: The API is provided "as is" without any guarantees. The developer is not responsible for any loss of data, service interruptions, or any other issues that may arise from the use of this API.
6.  **Changes & Termination**: The public endpoints can be changed, deprecated, or removed at any time without notice.
​
### Liability
​
By using this API, you agree to not hold the developer, Ernesto Gonzalez, liable for any damages or issues that may arise from the usage of this API. This includes but is not limited to data loss, inaccuracies, and any potential breaches of third-party terms of service.
​