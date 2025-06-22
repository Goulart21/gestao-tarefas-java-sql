import br.com.tarefas.exception.TarefaNaoEncontrada;
import br.com.tarefas.model.Tarefas;
import br.com.tarefas.repository.TaskDAO;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {

        System.out.println("Gestão de tarefas");
        TaskDAO dao = new TaskDAO();

        Scanner sc = new Scanner(System.in);
        while (true) {

            System.out.println("Digite a opção que deseja");
            System.out.println("1. Cadastrar tarefa");
            System.out.println("2. Concluir tarefa");
            System.out.println("3. Excluir tarefa");
            System.out.println("4. Atualizar tarefa");
            System.out.println("5. Mostrar todas as tarefas");
            System.out.println("6. Filtrar tarefas");
            System.out.println("7. Deletar todas as tarefas");
            int opcao = sc.nextInt();
            switch (opcao) {

                case 1:
                    System.out.print("Digite a descrição da tarefa: ");
                    sc.nextLine();
                    String descricao = sc.nextLine();
                    System.out.print("A tarefa está concluída? (s/n): ");
                    String resposta = sc.nextLine().trim().toLowerCase();
                    boolean concluida = resposta.equals("s");
                    Tarefas novaTarefa = new Tarefas();
                    novaTarefa.setDescricao(descricao);
                    novaTarefa.setConcluida(concluida);
                    System.out.println(novaTarefa.getDescricao() + " adicionada com sucesso1");

                    try {
                        dao.save(novaTarefa);
                    } catch (SQLException e) {
                        System.out.println("Erro ao salvar tarefa: " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.println("Digite o ID da tarefa que deseja concluir");
                        int id = sc.nextInt();
                        dao.completed(id,true);
                        System.out.println("Tarefa concluida");
                    }catch(InputMismatchException ex){
                        System.out.println("Erro: Digite um numero valido para o ID");
                    }

                    break;

                case 3:
                    System.out.println("Digite o id da tarefa que deseja excluir");
                    int id = sc.nextInt();
                    dao.delete(id);
                    System.out.println("Tarefa deletada");
                    break;

                case 4:
                    try {
                        System.out.println("Digite o id da tarefa que deseja editar:");
                        id = sc.nextInt();

                        System.out.println("Digite a nova descrição");
                        sc.nextLine();
                        String novaDescricao = sc.nextLine();

                        System.out.print("A tarefa está concluída? (s/n): ");
                        String respostaNova = sc.nextLine().trim().toLowerCase();
                        boolean concluidaNova = respostaNova.equals("s");

                        Tarefas tarefaAtualizada = new Tarefas();
                        dao.update(id,novaDescricao,concluidaNova);
                        System.out.println(tarefaAtualizada.getDescricao() + " Atualizada");
                    }catch (TarefaNaoEncontrada ex){
                        System.out.println("Erro: " + ex.getMessage());

                    }

                    break;


                case 5:
                    dao.show();
                    break;

                case 6:
                    System.out.println("Em andamento = a, Concluidas = c");
                    sc.nextLine();
                    resposta = sc.nextLine().trim().toLowerCase();

                    if(resposta.equals("a")){
                        dao.filter(false);
                    }
                    else if(resposta.equals("c")){
                        dao.filter(true);
                    }
                    else {
                        System.out.println("Digite uma opção valida");
                    }
                    break;

                case 7:
                    System.out.println("Deseja mesmo excluir todas as tarefas? (s/n)");
                    sc.nextLine();
                    resposta = sc.nextLine().trim().toLowerCase();
                    boolean afirmacao = resposta.equals("s");
                    if (afirmacao == true){
                        dao.deleteAll();
                    }
                    else {
                        System.out.println("Operação cancelada");
                    }
                    break;
            }
        }


    }
}