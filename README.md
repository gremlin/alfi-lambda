# alfi-lambda
Simple example showing how to use Gremlin's ALFI to attack a Lambda function

# Prerequisites
* A Gremlin account
* An AWS account
* Maven 2+
* Java 8+

# Steps to get up and running
## AWS Lambda setup
* Log into the AWS console, navigate to the Lambda service and select the bright orange button that says "Create Function".
![Create Function 1](img/1.png)
* Leave the preselected "Author from Scratch" tile selected, give your function a name, give your function a name, and finally click the bright orange button that says "Create Function".
![Create Function 2](img/2.png)
* Once your function is created, scroll down to the "Environment Variables" section then click the white "Manage environment variables" button.
![Create Function 3](img/3.png)
* On the next screen, click the white "Add environment variable" button.
![Create Function 4](img/4.png)
* Add the following environment variables then click the bright orange "Save" button.

*NOTE:* The newlines `\n` in the certificate and private keys.

| Key        | Value           | 
| ------------- |:-------------:|
| GREMLIN_ALFI_ENABLED      | true |
| GREMLIN_ALFI_IDENTIFIER      | LAMBDADEMO      |
| GREMLIN_TEAM_CERTIFICATE_OR_FILE | -----BEGIN CERTIFICATE-----\n<certificate output>\n-----END CERTIFICATE-----|
| GREMLIN_TEAM_PRIVATE_KEY_OR_FILE | -----BEGIN CERTIFICATE-----\n<certificate output>\n-----END CERTIFICATE-----|
| GREMLIN_TEAM_ID | < Your Team ID > |

![Create Function 5](img/5.png)

* Scroll up to the "Function code" section then replace the default "Handler" input text with `com.alfilambda.AlfiDemoHandler::handleRequest`.
![Create Function 6](img/6.png)


* Clone this repository, change to its directory then execute `mvn clean package`

* From the same "Function code" section, click the "Upload" button. Upload `alfi-lambda/target/alfi-lambda-1.0-SNAPSHOT-jar-with-dependencies.jar`. Then click the orange "Save" button at the top right of your screen.

![Create Function 8](img/8.png)

* Once the upload completes, ignore the blue notification about the lambda being too large to enable inline code editing. Click the Test button to run the function.

![Create Function 9](img/9.png)

* Give your event a name then scroll to the orange "Create" button at the bottom right of the screen

![Create Function 10](img/10.png)

* Click the "Test" button again. The first time you run your lambda, you will have to wait a few seconds due to the cold start problem

![Create Function 11](img/11.png)

* When the function completes, expand the details and note the return body of `"200 OK"` and the log message of `Lambda took 7136 millis`. Of course the lambda execution time will vary.

![Create Function 12](img/12.png)

**Congratulations! You're now ready to setup a Gremlin ALFI Experiment to attack this Lambda Function**