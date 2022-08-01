## User Spring Boot application

Spring Boot application for users.
You can create, get, update and delete user.

### Getting started
Download this project and download necessary tools.

#### Maven

##### On ubuntu run:

`sudo apt-get install maven`

`mvn clean package spring-boot:repackage`

##### On mac os run:

`brew update`

`brew install maven`

`mvn package`

#### Database
This project uses Postgresql 14.3

To install enter:

`sudo sh -c 'echo "deb http://apt.postgresql.org/pub/repos/apt $(lsb_release -cs)-pgdg main" > /etc/apt/sources.list.d/pgdg.list'`

`wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | sudo apt-key add -`

`sudo apt update`

`sudo apt -y install postgresql-14.3` 

### Run
Compile the application using:
`mvn package`

Compiled file will be in the target directory.

Use this command to run the project:
`java -jar user-app-1.0-SNAPSHOT.jar`

### Using the application

The application uses memory or Postgres repositories.

To use memory repository, change the `getUserRepository` method in configuration file *RepositoryConfiguration.java* like this:

```
@Bean
  public IUserRepository getUserRepository() {
    return new UserRepository();
  }
```

To use Postgres repository, change the same method like this:

```
@Bean
  public IUserRepository getUserRepository(final JdbcOperations jdbcOperations) {
    return new PostgresUserRepository(jdbcOperations);
  }
```

Requests which can do with a user:

#### POST-request to /user
Create a user with the request body.

```
{ 
    "userId": "example-UUID",
    "userFullName": "Surname Name MiddleName"
} 
```

#### GET-request to /user
Get the user by his id.

```
{ 
    "userId": "example-UUID"
} 
```


#### POST-request to /user/update
Update an existing user. User id remains untouched.

```
{ 
    "userId": "example-UUID",
    "userFullName": "New User Name"
} 
```

#### DELETE-request to /user
Delete the user by his id.

```
{ 
    "userId": "example-UUID"
} 
```

