# seia-endereco

API responsável pela disponibilização de Endereços.

# Endpoints disponíveis:

## /seia-endereco/api/endereco?cep={cep} 
Recebe um cep e realiza busca em determinadas fontes, caso o endereço não esteja presente no database local e cria-se uma cópia do registro informando o grau de credibilidade da fonte. 
Atualmente os Correios possui o maior grau de confiabilidade. 


## /seia-endereco/api/endereco/{id}
Recebe um id e realiza a busca no banco de dados referente ao mesmo


# Instalação Wildfly
    1. Baixa e descompactar o Wildfly na pasta desejada
    2. Acessar a pasta bin do Wildfly e abrir o arquivo standalone.conf e cofigurar o JAVA_HOME="$JAVA_HOME" e JBOSS_HOME=”[caminho]/wildfly-10.1.0.Final”
    3. Na linha de comando executar ./standalone.sh para iniciar e testar o servidor
    
Se tudo ocorreu bem, será possível acessar a página de boas vindas do wildfly http://localhost:8080

# Adicionando usuário admin no Wildfly
    1. Acessar a pasta bin do Wildfly
    2. Abrir a linha de comando
    3. Digitar sh add-user.sh e pressionar Enter
    4. Seguir passos da instalação
    
    Obs. Para facilitar a utilização e migração do server, informar usuário e senha como "jboss"
    
Se tudo ocorreu bem, será possivel acessar a tela de administração do wildfly http://localhost:9990

# Instalando driver no Wildfly
    1. Baixe o drive na extensão .jar do postgres (http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22org.postgresql%22%20AND%20a%3A%22postgresql%22)
    2. Crie uma pasta /drivers dentro da pasta do Wildfly e adicione o driver
    3. Abra a linha de comando e acesse o bin
    4. Digitar o comando a seguir e pressionar Enter
        ./jboss-cli.sh --connect
    5. Digitar o comando a seguir e pressionar Enter
        module add --name=org.postgres --resources=/[caminho do wildfly]/drivers/postgresql-42.2.1.jre7.jar --dependencies=javax.api,javax.transaction.api
    6. Digitar o comando a seguir e pressionar Enter 
        /subsystem=datasources/jdbc-driver=postgres:add(driver-name="postgres",driver-module-name="org.postgres",driver-class-name=org.postgresql.Driver)
    
Se tudo ocorreu bem, o console irá exibir a mensagem {"outcome" => "success"}
    
# Adicionando Datasource
    Com o Wildfly iniciado, realize os seguintes passos
    
    1. Acesse a tela de administração http://localhost:9990
    2. Acesse o menu Configuration e depois Subsystems > Datasources >Non -XA e acione o botão Add
    3. Selecione PostgreSQL Datasource e acione Next
    4. Informe o Name "SeiaEndereco" e JNDI Name "java:jboss/datasources/SeiaEndereco" e acione Next
    5. Clique Detected Driver e selecione o driver postgres, após isso acione Next
    6. Connection Url informe "jdbc:postgresql://[ip]:[porta]/seia_endereco", Username [username], Senha [password]
        e acione Next
    7. Acione Finish

Se tudo ocorreu bem, o Servidor irá exibir a mensagem de sucesso "Added Datasource SeiaEndereco"
    
Para testar a conexão, clique no Dropdown List ao lado do Datasource criado e então em Test Connection, a mensagem "Successfully created JDBC connection." 
deverá ser exibida

# Configurando reconexão do dataSource
    1. Acesse a pasta [caminho do projeto]/standalone/configuration
    2. Abra o arquivo standalone.xml
    3. Localize a seção do datasource configurado no passo anterior
    4. Dentro de <validation> adicione:
        <background-validation-millis>3000</background-validation-millis>
        <validate-on-match>false</validate-on-match>
    5. Reinicie o servidor
  
    
