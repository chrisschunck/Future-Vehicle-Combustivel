# 🚗 Frota Control — Gestão de Veículos Elétricos e Combustíveis Futuros
Projeto desenvolvido com o objetivo de aplicar conceitos de Arquitetura Hexagonal (Ports & Adapters) utilizando Spring Boot, explorando boas práticas de desenvolvimento, separação de responsabilidades, persistência com Spring Data JPA, DTOs e interface web com Thymeleaf.

# 📌 Objective
Gerenciar a frota de veículos elétricos (e de combustíveis futuros) de uma empresa através de uma API REST e de uma interface web.
A aplicação permite:
Cadastrar veículos com diferentes fontes de energia (BEV, FCEV, Híbrido) Cadastrar motoristas Alocar veículos a motoristas Finalizar alocações Registrar manutenções preventivas Registrar abastecimentos e recargas Consultar o status da frota em tempo real Interagir pela API REST ou pela interface web

# ⚙️ Technologies
Java 21+ Spring Boot 4.1 Spring Web (MVC) Spring Data JPA Hibernate Spring Security Bean Validation Thymeleaf Bootstrap 5 H2 Database Lombok Maven Git / GitHub

# 🧠 Business Logic
A aplicação foi desenvolvida seguindo os princípios da Arquitetura Hexagonal, separando claramente as responsabilidades entre domínio, aplicação e infraestrutura.

Domain
Contém os models de negócio puros (Veiculo e suas variações BEV/FCEV/Híbrido, Motorista, Alocacao, Manutencao, Abastecimento, Empresa), sem nenhuma dependência de framework. As regras de negócio (ex: um veículo só pode ter uma alocação ativa por vez, validação de CNH vencida) vivem nessa camada.

Application (Ports & Use Cases)
Define os ports de entrada (casos de uso, ex: AlocarVeiculoUseCase) e de saída (repositórios, ex: VeiculoRepositoryPort), e implementa os Services que orquestram as regras de negócio entre eles.

Adapters — Controllers (entrada)
Responsáveis por disponibilizar os endpoints REST da aplicação (/api/veiculos, /api/motoristas, /api/alocacoes, /api/manutencoes, /api/abastecimentos) e as rotas da interface web (/veiculos, /motoristas, /alocacoes, /manutencoes, /abastecimentos), sem conter regra de negócio — apenas tradução de entrada/saída.

Adapters — Repository (Persistence)
A camada de persistência utiliza Spring Data JPA.
Os Repository Adapters fazem a comunicação entre a aplicação e o banco de dados, convertendo os models de domínio em entidades JPA e vice-versa.

DTO Request / Response
Os DTOs Request recebem os dados enviados pelo cliente na API, evitando que as entidades sejam expostas diretamente.
Os DTOs Response retornam apenas as informações necessárias para o cliente.
Para a interface web, formulários usam classes de FormData dedicadas para o binding do Thymeleaf.

Interface Web (Thymeleaf)
Um dashboard e telas de cadastro/consulta foram construídos com Thymeleaf + Bootstrap, permitindo operar o sistema completo pelo navegador, sem depender apenas da API REST.

Exception Handler
O projeto possui tratamento centralizado de exceções através do:
GlobalExceptionHandler
Permitindo respostas padronizadas para erros da API (404 para entidade não encontrada, 409 para conflitos de regra de negócio, 400 para dados inválidos).

# ▶️ Execution
mvn spring-boot:run
Aplicação disponível em: http://localhost:8080

# 🗄️ Database
O sistema utiliza o banco H2 Database (modo arquivo) para persistência dos dados.
O banco é inicializado automaticamente pelo Spring Boot.
Console do H2 disponível em:
http://localhost:8080/h2-console

# 👨‍🏫 Assessment
Critérios atendidos ✅ Arquitetura Hexagonal (Ports & Adapters) ✅ API REST ✅ Interface Web (Thymeleaf) ✅ Spring Boot ✅ Spring Data JPA ✅ CRUD completo ✅ DTO Request ✅ DTO Response ✅ Bean Validation ✅ Tratamento global de exceções ✅ Banco H2 ✅ Organização em camadas ✅ Herança JPA (JOINED) para tipos de veículo ✅ Boas práticas de desenvolvimento ✅ Documentação

# 🚀 Future Features
Autenticação com Spring Security 
+ JWT Módulo de Locadora (contrato de locação)
+ Módulo de Concessionária (pátio e test-drive)
+ Banco PostgreSQL
+ Docker 
+ Testes Unitários
+ Testes de Integração Telemetria via IoT/MQTT
+ Notificações por e-mail CI/CD com GitHub Actions
