package br.com.FutureVehiclesAndFuel.adapters.in.web.view;

import br.com.FutureVehiclesAndFuel.adapters.in.web.view.formdata.AlocacaoFormData;
import br.com.FutureVehiclesAndFuel.adapters.in.web.view.viewmodel.AlocacaoViewRow;
import br.com.FutureVehiclesAndFuel.application.port.in.*;
import br.com.FutureVehiclesAndFuel.application.port.in.command.AlocarVeiculoCommand;
import br.com.FutureVehiclesAndFuel.domain.enums.StatusVeiculo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/alocacoes")
public class AlocacaoViewController {

    private final AlocarVeiculoUseCase alocarVeiculoUseCase;
    private final FinalizarAlocacaoUseCase finalizarAlocacaoUseCase;
    private final ConsultarAlocacoesUseCase consultarAlocacoesUseCase;
    private final ConsultarVeiculosUseCase consultarVeiculosUseCase;
    private final ConsultarMotoristasUseCase consultarMotoristasUseCase;

    public AlocacaoViewController(AlocarVeiculoUseCase alocarVeiculoUseCase,
                                   FinalizarAlocacaoUseCase finalizarAlocacaoUseCase,
                                   ConsultarAlocacoesUseCase consultarAlocacoesUseCase,
                                   ConsultarVeiculosUseCase consultarVeiculosUseCase,
                                   ConsultarMotoristasUseCase consultarMotoristasUseCase) {
        this.alocarVeiculoUseCase = alocarVeiculoUseCase;
        this.finalizarAlocacaoUseCase = finalizarAlocacaoUseCase;
        this.consultarAlocacoesUseCase = consultarAlocacoesUseCase;
        this.consultarVeiculosUseCase = consultarVeiculosUseCase;
        this.consultarMotoristasUseCase = consultarMotoristasUseCase;
    }

    @GetMapping
    public String listar(Model model) {
        var rows = consultarAlocacoesUseCase.listarTodas().stream()
                .map(a -> new AlocacaoViewRow(
                        a.getId(),
                        consultarVeiculosUseCase.buscarPorId(a.getVeiculoId()).getPlaca(),
                        consultarMotoristasUseCase.buscarPorId(a.getMotoristaId()).getNome(),
                        a.getDataInicio(), a.getDataFim(), a.getKmInicial(), a.getKmFinal(), a.estaAtiva()))
                .toList();
        model.addAttribute("alocacoes", rows);
        return "alocacoes/list";
    }

    @GetMapping("/nova")
    public String formularioNovo(Model model) {
        var veiculosDisponiveis = consultarVeiculosUseCase.listarTodos().stream()
                .filter(v -> v.getStatus() == StatusVeiculo.DISPONIVEL)
                .toList();
        model.addAttribute("alocacaoFormData", new AlocacaoFormData());
        model.addAttribute("veiculosDisponiveis", veiculosDisponiveis);
        model.addAttribute("motoristas", consultarMotoristasUseCase.listarTodos());
        return "alocacoes/form";
    }

    @PostMapping
    public String alocar(@ModelAttribute AlocacaoFormData form, RedirectAttributes redirectAttributes) {
        try {
            AlocarVeiculoCommand command = new AlocarVeiculoCommand(
                    form.getVeiculoId(), form.getMotoristaId(), form.getKmInicial());
            alocarVeiculoUseCase.alocar(command);
            redirectAttributes.addFlashAttribute("sucesso", "Veiculo alocado com sucesso.");
            return "redirect:/alocacoes";
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("erro", "Nao foi possivel alocar: " + ex.getMessage());
            return "redirect:/alocacoes/nova";
        }
    }

    @PostMapping("/{id}/finalizar")
    public String finalizar(@PathVariable Long id, @RequestParam Integer kmFinal,
                             RedirectAttributes redirectAttributes) {
        try {
            finalizarAlocacaoUseCase.finalizar(id, kmFinal);
            redirectAttributes.addFlashAttribute("sucesso", "Alocacao finalizada com sucesso.");
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("erro", "Nao foi possivel finalizar: " + ex.getMessage());
        }
        return "redirect:/alocacoes";
    }
}
