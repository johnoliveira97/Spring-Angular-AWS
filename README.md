# Spring-Angular-AWS
Crud - Livraria com Spring (Java 17) e Angular 14. Subindo na AWS.

O link base, para acessar a app Back-End: http://livrariamaua.sa-east-1.elasticbeanstalk.com/

O link base para acessar a tela da app (Front-End): http://livrariamaua-web.s3-website-sa-east-1.amazonaws.com/

Foram utilizados o Elastic Beanstalk, para subir a aplicação, redirecionando da porta 5000 para 80; S3 público, para hostear o site estático; DMS para criar arquivos csv, para gravar e auditar alterações no banco de dados.

EC2 (On-Demand) -> Spring App
S3 -> Angular App
RDS for MySQL com Read-Replica -> Banco Relacional
Secrets Manager -> Rotação automática de senha, armazenando senha do banco de dados
DMS -> CDC Replication
S3 -> Armazenamento de Arquivos CSV provenientes do DMS CDC Replication


**Rodando local**

Back-End: http://localhost:5000/
Front-End: http://localhost:4200/

**Utilização do SOLID**
O Crud foi feito como MicroServiço, contendo apenas funcionalidades referentes a Inclusão de Livros. Arquitetura MVC, usando SOLID como Design Pattern.
Cada classe possui uma única responsabilidade (S), o princípio de Open-Close (O), está sendo utilizado para alterações no Banco, o Liskov Substitution Principle (L), para configurações do Banco e Exceptions criadas para o código, O Interface Segregation, para alterações no Banco também, e o Dependency Invertion (D) está sendo utilizado em diversas classes, como por exemplo classes Service-Repository.
