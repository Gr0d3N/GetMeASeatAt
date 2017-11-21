# AWS Serverless - Alexa skill based on Unofficial OpenTable API
Ask Alexa if you can reserve a restaurant in your city with OpenTable.

## Overview
TODO

## Setup

### Build

```
gradle build

aws cloudformation package \
    --template-file template.yaml \
    --s3-bucket <S3_BUCKET> \
    --s3-prefix <S3_PREFIX> \
    --output-template-file packaged-template.yaml
```

### Deploy
```
aws cloudformation deploy \
     --template-file packaged-template.yaml \
     --stack-name <STACK_NAME>
```

### Alexa Skill Setup

TODO

### Examples

TODO