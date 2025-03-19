# Estágio de construção (build)
FROM openjdk:22-jdk-slim AS build

# Instale o Maven
RUN apt-get update && apt-get install -y maven

# Define o diretório de trabalho no contêiner
WORKDIR /app

# Copie apenas o pom.xml primeiro para aproveitar o cache de dependências
COPY pom.xml .

# Baixe as dependências (isso será armazenado em cache)
RUN mvn dependency:go-offline -B

# Copie o restante do código fonte
COPY src ./src

# Execute o comando mvn clean install
RUN mvn clean install -DskipTests

# Estágio final (imagem leve)
FROM openjdk:22-jdk-slim

# Define o diretório de trabalho no contêiner
WORKDIR /app

# Copie o arquivo JAR da sua aplicação da imagem de compilação para a imagem final
COPY --from=build /app/target/*.jar app.jar

# Exponha a porta que sua aplicação utiliza (opcional)
EXPOSE 8081

# Comando para executar a aplicação quando o contêiner for iniciado
ENTRYPOINT ["java", "-jar", "app.jar"]