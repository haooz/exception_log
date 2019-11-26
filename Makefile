EXPORT_PORT=5101
PROFILE="produce"

APP_ADMIN_TAG="registry.znkf.kangmochou.com:1579/znkf/app_admin"
image_name="${APP_ADMIN_TAG}_dev:19.11.01"

docker stop exception_log_${PROFILE}
docker rm -f exception_log_${PROFILE}

docker run -d \
-p ${EXPORT_PORT}:8686 \
--name exception_log_${PROFILE} \
-v /home/zkhc_zhh/exception_log/logs/${PROFILE}:/root/app/exception_log/${PROFILE} \
-v /home/zkhc_zhh/exception_log/target/exception_log.jar:/opt/app.jar \
-v /home/zkhc_zhh/exception_log/:/opt/exception_log \
-e spring.profiles.active=${PROFILE}  \
${image_name}


# 查看调试
docker exec -it exception_log_produce /bin/sh
