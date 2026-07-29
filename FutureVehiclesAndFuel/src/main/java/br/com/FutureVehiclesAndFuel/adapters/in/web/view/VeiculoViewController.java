package br.com.FutureVehiclesAndFuel.adapters.in.web.view;

import br.com.FutureVehiclesAndFuel.adapters.in.web.view.formdata.VeiculoFormData;
import br.com.FutureVehiclesAndFuel.application.port.in.CadastrarVeiculoUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.ConsultarVeiculosUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.command.CadastrarVeiculoCommand;
import br.com.FutureVehiclesAndFuel.domain.enums.TipoPropulsao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/veiculos")
public class VeiculoViewController {

    private final CadastrarVeiculoUseCase cadastrarVeiculoUseCase;
    private final ConsultarVeiculosUseCase consultarVeiculosUseCase;

    public VeiculoViewController(CadastrarVeiculoUseCase cadastrarVeiculoUseCase,
                                  ConsultarVeiculosUseCase consultarVeiculosUseCase) {
        this.cadastrarVeiculoUseCase = cadastrarVeiculoUseCase;
        this.consultarVeiculosUseCase = consultarVeiculosUseCase;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("veiculos", consultarVeiculosUseCase.listarTodos());
        return "veiculos/list";
    }

    @GetMapping("/novo")
    public String formularioNovo(Model model) {
        model.addAttribute("veiculoFormData", new VeiculoFormData());
        model.addAttribute("tiposPropulsao", TipoPropulsao.values());
        return "veiculos/form";
    }

    @PostMapping
    public String cadastrar(@ModelAttribute VeiculoFormData form, RedirectAttributes redirectAttributes) {
        try {
            CadastrarVeiculoCommand command = new CadastrarVeiculoCommand(
                    form.getPlaca(), form.getModelo(), form.getMarca(), form.getQuilometragemAtual(),
                    form.getEmpresaId(), form.getTipoPropulsao(), form.getCapacidadeBateriaKwh(),
                    form.getAutonomiaEstimadaKm(), form.getNivelCargaAtualPercentual(),
                    form.getCapacidadeTanqueH2Kg(), form.getPressaoTanqueBar(), form.getCapacidadeTanqueLitros());
            cadastrarVeiculoUseCase.cadastrar(command);
            redirectAttributes.addFlashAttribute("sucesso", "Veiculo cadastrado com sucesso.");
            return "redirect:/veiculos";
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("erro", "Nao foi possivel cadastrar: " + ex.getMessage());
            return "redirect:/veiculos/novo";
        }
    }
}
