package br.edu.ifsp.pep.listener;

import br.edu.ifsp.pep.controller.FuncionarioController;
import br.edu.ifsp.pep.model.Funcionario;
import jakarta.faces.application.NavigationHandler;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;
import jakarta.inject.Inject;
import java.io.IOException;

public class Autorizacao implements PhaseListener {

    @Inject
    private FuncionarioController funcionarioController;

    @Override
    public void afterPhase(PhaseEvent event) {
        System.out.println("After: " + event.getPhaseId());

        if (event.getPhaseId() == PhaseId.APPLY_REQUEST_VALUES) {
            System.out.println("Após a fase Apply Request Values.");
        }

        FacesContext ctx = event.getFacesContext();

        String pagina = ctx.getViewRoot().getViewId();

        System.out.println(pagina);

        Funcionario funcionarioAutenticado = funcionarioController.getFuncionarioLogado();

        if ((funcionarioAutenticado == null
                || !funcionarioAutenticado.getCargo().equals("G"))
                && (pagina.startsWith("/dashboard")
                || pagina.startsWith("/funcionario/"))) {
            NavigationHandler nh = ctx.getApplication()
                    .getNavigationHandler();

            nh.handleNavigation(ctx, null, "funcionario");
        }

    }

    @Override
    public void beforePhase(PhaseEvent event) {
        //    System.out.println("Before: " + event.getPhaseId());
//        FacesContext ctx = event.getFacesContext();
//        if (event.getPhaseId() == PhaseId.APPLY_REQUEST_VALUES
//                || event.getPhaseId() == PhaseId.INVOKE_APPLICATION
//                || event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
//            String pagina = ctx.getViewRoot().getViewId();
//            System.out.println("Path: " + pagina);
//            
//            if (pagina.equals("/financeiro/folhaPagamento.xhtml")) {
//                System.out.println("Redirecionando.....");
////                NavigationHandler nh = ctx.getApplication()
////                        .getNavigationHandler();
////                nh.handleNavigation(event.getFacesContext(),
////                        null, "erro");
//                try {
//                    ctx.getExternalContext().redirect("/phaselistener/index.xhtml");
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

}
