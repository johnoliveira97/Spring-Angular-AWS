# Spring-Angular-AWS
Crud - Livraria com Spring (Java 17) e Angular 14. Subindo na AWS.

O link base, para acessar a app Back-End: http://livrariamaua.sa-east-1.elasticbeanstalk.com/

O link base para acessar a tela da app (Front-End):

Foram utilizados o Elastic Beanstalk, para subir a aplicação, redirecionando da porta 5000 para 80; S3 público, para hostear o site estático; DMS para criar arquivos csv, para gravar e auditar alterações no banco de dados.

EC2 (On-Demand) -> Spring App
S3 -> Angular App
RDS for MySQL com Read-Replica -> Banco Relacional
Secrets Manager -> Rotação automática de senha, armazenando senha do banco de dados
DMS -> CDC Replication
S3 -> Armazenamento de Arquivos CSV provenientes do DMS CDC Replication
