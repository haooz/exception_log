docker build -t registry.znkf.kangmochou.com:1579/znkf/app_admin:19.11.02 .

app_admin_tag="registry.znkf.kangmochou.com:1579/znkf/app_admin:19.11.02"

docker push ${app_admin_tag}
docker tag ${app_admin_tag} registry.znkf.kangmochou.com:1579/znkf/app_admin:state
docker push registry.znkf.kangmochou.com:1579/znkf/app_admin:state
docker tag ${app_admin_tag} registry.znkf.kangmochou.com:1579/znkf/app_admin:latest
docker push registry.znkf.kangmochou.com:1579/znkf/app_admin:latest

# 推送命令
b="registry.znkf.kangmochou.com:1579/znkf/app_admin:19.11.02"
docker push ${b}

docker login registry.znkf.kangmochou.com:1579 -uzkhc_docker -pkjds.1017