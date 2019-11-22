FROM registry.znkf.kangmochou.com:1579/log-center-mysql:latest
ADD exception-log.cnf /etc/mysql/conf.d/my.cnf
ADD exceptionLog.sql /docker-entrypoint-initdb.d/exceptionLog.sql
EXPOSE 3306