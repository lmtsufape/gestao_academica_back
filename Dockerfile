# Use uma imagem base do OpenJDK 22
FROM openjdk:22-jdk-slim AS build

# Instale Maven
RUN apt-get update && apt-get install -y maven
# Define o diretório de trabalho no contêiner
WORKDIR /app


COPY . .

# Execute o comando mvn clean install
RUN mvn clean install -DskipTests

# Use uma imagem OpenJDK como base para a imagem final
FROM openjdk:22-jdk-slim

# Define o diretório de trabalho no contêiner
WORKDIR /app

# Copie o arquivo JAR da sua aplicação da imagem de compilação para a imagem final
COPY --from=build /app/target/*.jar app.jar

# Exponha a porta que sua aplicação utiliza (opcional)
EXPOSE 8081

# Comando para executar a aplicação quando o contêiner for iniciado
ENTRYPOINT ["java", "-jar", "app.jar"]