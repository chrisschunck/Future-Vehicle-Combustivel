package br.com.FutureVehiclesAndFuel.adapters.in.web.view;

import br.com.FutureVehiclesAndFuel.adapters.in.web.view.formdata.ManutencaoFormData;
import br.com.FutureVehiclesAndFuel.adapters.in.web.view.viewmodel.ManutencaoViewRow;
import br.com.FutureVehiclesAndFuel.application.port.in.ConsultarManutencoesUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.ConsultarVeiculosUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.RegistrarManutencaoUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.command.RegistrarManutencaoCommand;
import br.com.FutureVehiclesAndFuel.domain.enums.TipoManutencao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/manutencoes")
public class ManutencaoViewController {

    private final RegistrarManutencaoUseCase registrarManutencaoUseCase;
    private final ConsultarManutencoesUseCase consultarManutencoesUseCase;
    private final ConsultarVeiculosUseCase consultarVeiculosUseCase;

    public ManutencaoViewController(RegistrarManutencaoUseCase registrarManutencaoUseCase,
                                     ConsultarManutencoesUseCase consultarManutencoesUseCase,
                                     ConsultarVeiculosUseCase consultarVeiculosUseCase) {
        this.registrarManutencaoUseCase = registrarManutencaoUseCase;
        this.consultarManutencoesUseCase = consultarManutencoesUseCase;
        this.consultarVeiculosUseCase = consultarVeiculosUseCase;
    }

    @GetMapping
    public String listar(Model model) {
        var rows = consultarManutencoesUseCase.listarTodas().stream()
                .map(m -> new ManutencaoViewRow(
                        consultarVeiculosUseCase.buscarPorId(m.getVeiculoId()).getPlaca(), m))
                .toList();
        model.addAttribute("manutencoes", rows);
        return "manutencoes/list";
    }

    @GetMapping("/nova")
    public String formularioNovo(Model model) {
        model.addAttribute("manutencaoFormData", new ManutencaoFormData());
        model.addAttribute("veiculos", consultarVeiculosUseCase.listarTodos());
        model.addAttribute("tiposManutencao", TipoManutencao.values());
        return "manutencoes/form";
    }

    @PostMapping
    public String registrar(@ModelAttribute ManutencaoFormData form, RedirectAttributes redirectAttributes) {
        try {
            RegistrarManutencaoCommand command = new RegistrarManutencaoCommand(
                    form.getVeiculoId(), form.getTipo(), form.getQuilometragemRealizada(),
                    form.getDataRealizada(), form.getIntervaloProximaKm(), form.getCusto());
            registrarManutencaoUseCase.registrar(command);
            redirectAttributes.addFlashAttribute("sucesso", "Manutencao registrada com sucesso.");
            return "redirect:/manutencoes";
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("erro", "Nao foi possivel registrar: " + ex.getMessage());
            return "redirect:/manutencoes/nova";
        }
    }
}
