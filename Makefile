EXPORT_PORT=11052
PROFILE="produce"

APP_ADMIN_TAG="registry.znkf.kangmochou.com:1579/znkf/app_admin"
image_name="${APP_ADMIN_TAG}_dev:19.11.01"

docker stop exception_log_${PROFILE}
docker rm -f exception_log_${PROFILE}

docker run -d \
-p ${EXPORT_PORT}:80 \
--name exception_log_${PROFILE} \
-v /home/zkhc_zhh/exception_log/logs/${PROFILE}:/root/app/exception_log/${PROFILE} \
-v /home/zkhc_zhh/exception_log/target/exception_log.jar:/opt/app.jar \
-e spring.profiles.active=${PROFILE}  \
${image_name}


# 查看调试
docker exec -it exception_log_produce /bin/sh

PORT=1508
PORT_MANAGE=1509
PROFILE="dev"
HOST="172.17.0.1"
DATABASE_PORT="548"
ADMIN_URL="jdbc:mysql://${HOST}:${DATABASE_PORT}/znkf_admin?autoReconnect=true&useUnicode=true\
&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false"
ADMIN_USERNAME="zkhc_admin"
ADMIN_PASSWORD="Lykj.2018"
NEW_URL="jdbc:mysql://${HOST}:${DATABASE_PORT}/znkf_new?autoReconnect=true\
&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false"
NEW_USERNAME="znkf_new_user"
NEW_PASSWORD="LyTest,.2018"
SCM_URL="jdbc:mysql://${HOST}:${DATABASE_PORT}/znkf_scm?autoReconnect=true\
&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false"
SCM_USERNAME="znkf_scm_user"
SCM_PASSWORD="zkhc,.2018"
LOGGING_URL="jdbc:mysql://${HOST}:${DATABASE_PORT}/logging?autoReconnect=true\
&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false"
LOGGING_USERNAME="znkf_new_user"
LOGGING_PASSWORD="LyTest,.2018"
REDIS_PORT="551"
REDIS_PASSWORD="dev.13460cdb3efd4"

docker stop znkf_app_admin_${PROFILE}
docker rm -f znkf_app_admin_${PROFILE}

docker run -d \
-p ${PORT}:506 \
-p ${PORT_MANAGE}:509 \
--name znkf_app_admin_${PROFILE} \
-v /home/zkhc_zhh/app_admin/guns-admin/target/app_admin-2.0.1.jar:/opt/app.jar \
-e spring.profiles.active=${PROFILE}  \
-e spring.datasource.url=${ADMIN_URL}  \
-e spring.datasource.username=${ADMIN_USERNAME}  \
-e spring.datasource.password=${ADMIN_PASSWORD}  \
-e spring.client-datasource.url=${NEW_URL}  \
-e spring.client-datasource.username=${NEW_USERNAME}  \
-e spring.client-datasource.password=${NEW_PASSWORD}  \
-e spring.scm-datasource.url=${SCM_URL}  \
-e spring.scm-datasource.username=${SCM_USERNAME}  \
-e spring.scm-datasource.password=${SCM_PASSWORD}  \
-e spring.logging-datasource.url=${LOGGING_URL}  \
-e spring.logging-datasource.username=${LOGGING_USERNAME}  \
-e spring.logging-datasource.password=${LOGGING_PASSWORD}  \
-e spring.redis.host=${HOST}  \
-e spring.redis.port=${REDIS_PORT}  \
-e spring.redis.password=${REDIS_PASSWORD}  \
registry.znkf.kangmochou.com:1579/znkf/app_admin_dev:19.11.01