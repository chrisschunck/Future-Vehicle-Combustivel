package br.com.FutureVehiclesAndFuel.adapters.in.web.view;

import br.com.FutureVehiclesAndFuel.adapters.in.web.view.formdata.MotoristaFormData;
import br.com.FutureVehiclesAndFuel.application.port.in.CadastrarMotoristaUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.ConsultarMotoristasUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.command.CadastrarMotoristaCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/motoristas")
public class MotoristaViewController {

    private final CadastrarMotoristaUseCase cadastrarMotoristaUseCase;
    private final ConsultarMotoristasUseCase consultarMotoristasUseCase;

    public MotoristaViewController(CadastrarMotoristaUseCase cadastrarMotoristaUseCase,
                                    ConsultarMotoristasUseCase consultarMotoristasUseCase) {
        this.cadastrarMotoristaUseCase = cadastrarMotoristaUseCase;
        this.consultarMotoristasUseCase = consultarMotoristasUseCase;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("motoristas", consultarMotoristasUseCase.listarTodos());
        return "motoristas/list";
    }

    @GetMapping("/novo")
    public String formularioNovo(Model model) {
        model.addAttribute("motoristaFormData", new MotoristaFormData());
        return "motoristas/form";
    }

    @PostMapping
    public String cadastrar(@ModelAttribute MotoristaFormData form, RedirectAttributes redirectAttributes) {
        try {
            CadastrarMotoristaCommand command = new CadastrarMotoristaCommand(
                    form.getNome(), form.getCnh(), form.getValidadeCnh(), form.getEmpresaId());
            cadastrarMotoristaUseCase.cadastrar(command);
            redirectAttributes.addFlashAttribute("sucesso", "Motorista cadastrado com sucesso.");
            return "redirect:/motoristas";
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("erro", "Nao foi possivel cadastrar: " + ex.getMessage());
            return "redirect:/motoristas/novo";
        }
    }
}
