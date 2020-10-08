# Coppers Odds Shoe Raffle Service

## What is Coppers Odds?

**Coppers Odds** is an organization started by my friend used to give away shoes for the use of raffle tickets. The goal of this is to make the raffle ticket cheap, and make it easily buyable so everyone can have a chance to win a shoe. Of course, this project will take a long time, from GUIs to using the Paypal Rest API, but it is definately achievable.

## Project Setup

My project is mainly made from Java, but it also uses other languages such as PHP and HTML too. Currently, I am using Maven for this project, so there will be a **pom.xml** file that will contain information about the repository itself. I'm using Maven so managing dependencies will be easier.

## Dependencies 

This project uses a variety of dependencies. I will list all of them here:

 - **GSON**, a google library used to parse JSON files. This would be used to parse a JSON file that would store data that the program can load. The github repository is located [here](https://github.com/google/gson).
 - **Javax.mail**, a library made from javax that is used to send emails. This would be used to account verification, resetting passwords, etc. The maven repository is located [here](https://mvnrepository.com/artifact/javax.mail/mail).
 - **SLF4J**, **SLF4J - Log4J**, and **Log4J**. These libraries are vital to the program, a they help make log files for the program. The maven repository is located [here](https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j).
 - **Paypal SDK**, this library allows my program to make a paypal payment when users buy tickets. Maven is shown here: 
 ```
		<dependency>
			<groupId>com.paypal.sdk</groupId>
			<artifactId>rest-api-sdk</artifactId>
			<version>LATEST</version>
		</dependency>
```		
 - **JSON Simple**, another JSON parsing library used for parsing JSON files. The maven repository is [here](https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple).
 - **Javax.servlet**, a library used for making servlets. Ideal for when making calls to the papal server using the rest api. The maven repository is here: [here](https://mvnrepository.com/artifact/javax.servlet/servlet-api).

## Note About the Github Repository

~~Unfortuantly, I wasn't able to upload any song files onto github, so you won't be able to listen or choose music, as it will throw an IOException. If you want to play around with the code, I will upload a release that contains the .mp3 to check if it is working or not. Thanks.~~ Edit: I was able to fix this by using git lfs here: https://git-lfs.github.com/ Now, you should be able to just download the repository. 

## How to Run this Application
Download the repository as a zip file, then import it into eclipse. The main class is ShoeRaffleService.java, so click the run button when you are in that class.
## Branches

I have two branches. Master, and dev (development). I use Master to push updates, and I use dev for development. I suggest for people who are doing code checks to use the dev branch, as it contains the latest updated code.

## Code Check

If you are here for code check, thank you, and I hope my code looks ok. Make pull requests, open issues, or send emails to brandonli2006ma@gmail.com  . I am a newbie at Java, and may make some mistakes. Here are some current issues I have as in right now.

- Paypal Payments Have Issues. Links for redirect, return, etc won't work. Issue opened [here](https://github.com/paypal/PayPal-Java-SDK/issues/387).
- Null Pointer Exception because of problems having to do with getting the value/key of an element in a ConcurrentHashMap. This problem is happening to Sessions, Accounts, and Payments.

- Thank you hchen for doing a code check
