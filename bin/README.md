# Welcome to Bin
A place with helpful utilities to help with the project.

## encrypt-value.sh
This shell script helps you generate an encrypted value so that you can
safely add it to the project and be part of the source. Remember to keep
the password hidden for the encryptor.  Do not reveal the encryptor
password in the source. Instead, pass that as an environment variable from your
runtime of choice. 

`encrypt-value.sh` uses a maven plugin to help.  Just need maven 
installed on your system.  `sdk install maven 3.8.1`
Reference: [jasypt-maven-plugin](https://github.com/ulisesbocchio/jasypt-spring-boot#maven-plugin)