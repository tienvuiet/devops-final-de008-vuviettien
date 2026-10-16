student.env
thong tin  APP_PORT="8091"
COMPOSE_PROJECT_NAME="nguyenvana-de001"
Dockerfile:
EXPOSE 8080

docker-compose.yml
app:
STUDENT_NAME: ${STUDENT_NAME}
STUDENT_ID: ${STUDENT_ID}
STUDENT_CLASS: ${STUDENT_CLASS}
STUDENT_EMAIL: ${STUDENT_EMAIL}
GITHUB_USERNAME: ${GITHUB_USERNAME}

controller:
@GetMapping("/submission")
public Map<String, Object> submission() {
Map<String, String> env = System.getenv();
return Map.of(
"student", Map.of(
"name", env.getOrDefault("STUDENT_NAME", ""),
"id", env.getOrDefault("STUDENT_ID", ""),
"class", env.getOrDefault("STUDENT_CLASS", ""),
"email", env.getOrDefault("STUDENT_EMAIL", ""),
"github", env.getOrDefault("GITHUB_USERNAME", "")
),
"version", env.getOrDefault("APP_VERSION", "unknown"),
"labs", findAll()
);
}

nginx/default.conf
location = / {
return 302 /api/submission;
}

SERVER_HOST: 221.121.3.208
SERVER_USER:tienvv-k24cntt4
SERVER_SSH_KEY



anh3: ssh tienvv-k24cntt4@221.121.3.208
cd ~/devops-test14
docker compose --env-file student.env ps
docker compose --env-file student.env logs --tail=100 app
docker compose --env-file student.env logs --tail=100 nginx
docker compose --env-file student.env logs --tail=100 mysql

