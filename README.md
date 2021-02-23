# JSF-task-manager


O sistema serve para gestão de tarefas, foi feito com Postgresql 12, JSF e SpringBoot 2.4.3 e utilizando-se o Java 11


## Como rodar

É necessário baixar o postgres https://www.postgresql.org/download/ e após isto feito, criar uma base de dados chamada "taskdb"

O arquivo `application.properties` está configurado para receber um usuário chamado "postgres" com senha "123456" por default.

Após a conexão com o banco, basta abrir e rodar a base da aplicação no arquivo `TaskSystemApplication` 


## Sobre o Projeto

Ao rodar localmente, ele possuí as seguintes urls disponíveis:

* localhost:8080/api/v1/task
* localhost:8080/listtasks.xhtml
* localhost:8080/posttask.xhtml

A url `localhost:8080/api/v1/task` é um HTTP GET que retorna todas as tarefas cadastradas no sistema, e assim mostrará todos os dados da tarefa como:

* id
* título
* descrição
* responsável
* prioridade
* data de criação
* deadline ( em segundos )
* status da tarefa

A url `localhost:8080/listtasks.xhtml` mostrará somente as tarefas que estão com status "em aberto", nela é possível filtrar as tarefas por id, título e responsável
Há 3 botões disponíveis por tarefa criada

* O 1º serve para atualizar os dados sobre título ou responsável da tarefa
* O 2º serve para deletar uma tarefa do sistema
* O 3º serve para tornar a tarefa como concluída, desta forma removendo ele da visualização desta página ( porém ainda é visível pelo GET request da api/v1/task )

A url `localhost:8080/posttask.xhtml` criará uma nova tarefa, onde lá poderá ser especificado, título, descrição, responsável, prioridade e o deadline da tarefa.


