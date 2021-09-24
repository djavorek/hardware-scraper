# hardware-scraper
Web scraper to find hardware prices on online markets. Ready to be run as Google Cloud Function.

## Usage

### Prerequisite

Install and initialize gcloud CLI: https://cloud.google.com/sdk/gcloud

## Run locally (TBD)


## Deploy to Google Cloud Functions

Fill the following template and run it in the project root (where pom.xml is located).

```
gcloud functions deploy {CLOUD-FUNCTION-NAME} --entry-point hu.javorekdenes.hwscraper.Function --runtime java11 --service-account {SERVICE-ACCOUNT} --trigger-topic {PUB-SUB-TOPIC-NAME} --region {REGION} 
```

### Full example

```
gcloud functions deploy hwscraper --entry-point hu.javorekdenes.hwscraper.Function --runtime java11 --service-account hardverapro-notifier@hardverapro-notifier.iam.gserviceaccount.com --trigger-topic scrape-trigger --region europe-west1 
```


## Architecture (draft)

This is the primary part of the following architecture

![architecture-diagram drawio](https://user-images.githubusercontent.com/35934971/133733496-442b7047-fbf3-4e34-af48-4313f0f9e03c.png)
