# line-bot-spring-boot-flex

[![Build Status](https://travis-ci.org/iphayao/line-bot-spring-boot-flex.svg?branch=master)](https://travis-ci.org/iphayao/line-bot-spring-boot-flex)

Code example in [medium](https://medium.com/@phayao/%E0%B8%AA%E0%B8%A3%E0%B9%89%E0%B8%B2%E0%B8%87-flex-message-%E0%B9%81%E0%B8%9A%E0%B8%9A%E0%B8%95%E0%B9%88%E0%B8%B2%E0%B8%87%E0%B9%86-%E0%B9%83%E0%B8%99-line-bot-%E0%B8%94%E0%B9%89%E0%B8%A7%E0%B8%A2-spring-boot-187b5ec1d56)'s story **[Create Flex Message in LINE Bot with Spring Boot]**

# How to run
After cloned this repo into your local machine, if you want to run locally, follow this step.

## Configuration .yml file
Reanme application.yml file `src/main/resources/application-example.yml` to `src/main/resources/application.yml` replace `channel-token` and `channel-secret` value with your particular values from LINE developer console.

```yml:application.yml
line.bot:
  channel-token: 'Put Your Channel Token Here.'
  channel-secret: 'Put Your Channel Secret Here.'
  handler.path: /callback
```

## Run Spring Boot
To run application in your local machine use this command:

```
$ mvn spring-boot:run

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.0.4.RELEASE)

........

INFO 35569 --- [main] com.iphayao.linebot.Application   : Started Application in 4.393 seconds (JVM running for 9.056)
```

# Done! 完了