# ProjetoSoftwareCopaDoMundo
Modelo e implementação ao backend em forma de API RESTFul do sistema de classificação da Copa do Mundo da Fifa


Na Api o usuario vai cadastrar 32 times, apos isso ele pode começar a gerar as partidas(Classificatoria, oitavas, finais e semi final) e documentando cada uma das partidas com quantidade de gols e cartoes de cada time, assim a api vai fazer o calculo e determinar a colocação dos três primeiros lugares.

O projeto foi feito em Java, e usamos banco de dados mongoDB
Para instalação:
-Clone do projeto
-Esperar baixar todas as dependencias
-ir em SistemaClassificacaoApplication e rodar

Se o senhor quiser acompanhar no banco de dados o processo terá que baixar o mongoDB compass
Dentro do APP vai aparecer "Paste your connection string"
do copiar e colar isso aqui:

mongodb+srv://julia:sistemaapi2022654@sistema-classificacao.6oeimf7.mongodb.net/admin?authSource=admin&replicaSet=atlas-2av83m-shard-0&w=majority&readPreference=primary&appname=MongoDB%20Compass&retryWrites=true&ssl=true

Para testar a api criamos algumas rotas já automatizada com os 32 times.
passo a passo:
1- cadastrar Time: http://localhost:9090/teste/geradorTimes

2- Gerar Partidas Classificatorias: http://localhost:9090/teste/gerarPartidaClassificatorias
3- Documentar Partidas: http://localhost:9090/teste/testeDocumentarPartida

4- Gerar Partidas Oitavas: http://localhost:9090/teste/gerarPartidaOitavas
5- Documentar Partidas: http://localhost:9090/teste/testeDocumentarPartida

6- Gerar Partidas quartas: http://localhost:9090/teste/gerarPartidaFinais
7- Documentar Partidas: http://localhost:9090/teste/testeDocumentarPartida

8- Gerar Partidas finais: http://localhost:9090/teste/gerarPartidaFinais
9- Documentar Partidas: http://localhost:9090/teste/testeDocumentarPartida

10- Gerar Partidas semi e final: http://localhost:9090/teste/gerarPartidaFinais
11- Documentar Partidas: http://localhost:9090/teste/testeDocumentarPartida

