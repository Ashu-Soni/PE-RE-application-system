# PE/RE Application System

## Description
--------------
The PE/RE Application aims to cater to both Professors’ and Students’ needs so that with least efforts a professor can display all the details he/she needs to display about the electives they offer and also mention about the number of students they need under them.After this the student just needs to check the portal for any such updates and apply if they find their interests aligned with the electives.

This web application provides a fair means wherein there is complete transparency about the electives posted and the professor has an option to either accept/reject the students application based on the student’s description posted along request sent.Also once rejected the student is not allowed to apply for the same elective again.

## Features
-----------

#### Features of Faculty login
- Faculty can post elective(research/project) description
- Faculty can edit the elective details
- Faculty can view the applications received from the students
- Faculty can accept/reject the applications.
- Faculty can search the application details based on student’s name

#### Features of Student login
- Students can view the electives
- Students can apply for electives
- Once rejected by professor he/she cannot apply to same elective
- Students can search the electives based on professor name

## Tools and Technologies used
------------------------------
- Git-version control system
- GitHub - Source Code Management
- Jenkins - Continuous Integration
- Docker - Containerization
- Ansible - Continuous Deployment
- Docker Compose - Container orchestration 

## Getting Started
------------------

#### Pre-requisite
- Docker
- Docker-compose

#### Clone the repository
```sh
git clone https://github.com/Ashu-Soni/PE-RE-application-system.git
```

#### Change the working directory
```sh
cd PE-RE-application-system
```

#### Change the proxy URL in ./frontend/src/Paths.js file at line #23
change to http://localhost:9090/

#### Create Images for frontend and backend
```sh
cd frontend
docker build -t <frontend_image_name> .
```

```sh
cd ../spe_majorProject
docker build -t <backend_image_name> .
```

#### Change the names in docker-compose.yml file
In frontend service, change image name to <frontend_image_name> and in backend service, change name to <backend_image_name>

#### start the docker containers through Docker-Compose
this will create three docker containers named mysql(database), backend(spring-boot) and frontend(react)

```sh
docker-compose up
```

#### 


## Development
--------------

You can use Jenkins pipeline for Development, Continuous Integration and Continuous Deployment

#### Pre-requisite
- Jenkins-server

#### Steps

- Create Jenkins pipeline and use the option "pipeline through SCM" and add this git repository into it. Add "Jenkinsfile" into `Script Path`.

- Additionally, you need to change the IP address into the `inventory` file. This IP refers to the remote machine where the application need to deployed.

- Setup SSH connect between your Jenkins-server and remote machine, because Ansible uses SSH protocol for the communication