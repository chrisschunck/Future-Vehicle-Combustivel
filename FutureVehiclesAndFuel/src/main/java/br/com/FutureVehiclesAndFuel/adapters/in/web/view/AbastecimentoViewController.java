package br.com.FutureVehiclesAndFuel.adapters.in.web.view;

import br.com.FutureVehiclesAndFuel.adapters.in.web.view.formdata.AbastecimentoFormData;
import br.com.FutureVehiclesAndFuel.adapters.in.web.view.viewmodel.AbastecimentoViewRow;
import br.com.FutureVehiclesAndFuel.application.port.in.ConsultarAbastecimentosUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.ConsultarVeiculosUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.RegistrarAbastecimentoUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.command.RegistrarAbastecimentoCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/abastecimentos")
public class AbastecimentoViewController {

    private final RegistrarAbastecimentoUseCase registrarAbastecimentoUseCase;
    private final ConsultarAbastecimentosUseCase consultarAbastecimentosUseCase;
    private final ConsultarVeiculosUseCase consultarVeiculosUseCase;

    public AbastecimentoViewController(RegistrarAbastecimentoUseCase registrarAbastecimentoUseCase,
                                        ConsultarAbastecimentosUseCase consultarAbastecimentosUseCase,
                                        ConsultarVeiculosUseCase consultarVeiculosUseCase) {
        this.registrarAbastecimentoUseCase = registrarAbastecimentoUseCase;
        this.consultarAbastecimentosUseCase = consultarAbastecimentosUseCase;
        this.consultarVeiculosUseCase = consultarVeiculosUseCase;
    }

    @GetMapping
    public String listar(Model model) {
        var rows = consultarAbastecimentosUseCase.listarTodos().stream()
                .map(a -> new AbastecimentoViewRow(
                        consultarVeiculosUseCase.buscarPorId(a.getVeiculoId()).getPlaca(), a))
                .toList();
        model.addAttribute("abastecimentos", rows);
        return "abastecimentos/list";
    }

    @GetMapping("/novo")
    public String formularioNovo(Model model) {
        model.addAttribute("abastecimentoFormData", new AbastecimentoFormData());
        model.addAttribute("veiculos", consultarVeiculosUseCase.listarTodos());
        return "abastecimentos/form";
    }

    @PostMapping
    public String registrar(@ModelAttribute AbastecimentoFormData form, RedirectAttributes redirectAttributes) {
        try {
            RegistrarAbastecimentoCommand command = new RegistrarAbastecimentoCommand(
                    form.getVeiculoId(), form.getQuantidade(), form.getCusto(), form.getLocalEletroposto());
            registrarAbastecimentoUseCase.registrar(command);
            redirectAttributes.addFlashAttribute("sucesso", "Abastecimento registrado com sucesso.");
            return "redirect:/abastecimentos";
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("erro", "Nao foi possivel registrar: " + ex.getMessage());
            return "redirect:/abastecimentos/novo";
        }
    }
}
