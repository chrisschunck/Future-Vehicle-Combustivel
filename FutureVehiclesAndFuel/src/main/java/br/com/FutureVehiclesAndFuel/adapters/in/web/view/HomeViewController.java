package br.com.FutureVehiclesAndFuel.adapters.in.web.view;

import br.com.FutureVehiclesAndFuel.application.port.in.ConsultarAlocacoesUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.ConsultarMotoristasUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.ConsultarVeiculosUseCase;
import br.com.FutureVehiclesAndFuel.domain.enums.StatusVeiculo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {

    private final ConsultarVeiculosUseCase consultarVeiculosUseCase;
    private final ConsultarMotoristasUseCase consultarMotoristasUseCase;
    private final ConsultarAlocacoesUseCase consultarAlocacoesUseCase;

    public HomeViewController(ConsultarVeiculosUseCase consultarVeiculosUseCase,
                               ConsultarMotoristasUseCase consultarMotoristasUseCase,
                               ConsultarAlocacoesUseCase consultarAlocacoesUseCase) {
        this.consultarVeiculosUseCase = consultarVeiculosUseCase;
        this.consultarMotoristasUseCase = consultarMotoristasUseCase;
        this.consultarAlocacoesUseCase = consultarAlocacoesUseCase;
    }

    @GetMapping("/")
    public String home(Model model) {
        var veiculos = consultarVeiculosUseCase.listarTodos();
        long disponiveis = veiculos.stream().filter(v -> v.getStatus() == StatusVeiculo.DISPONIVEL).count();
        long alocacoesAtivas = consultarAlocacoesUseCase.listarTodas().stream()
                .filter(a -> a.getDataFim() == null).count();

        model.addAttribute("totalVeiculos", veiculos.size());
        model.addAttribute("totalVeiculosDisponiveis", disponiveis);
        model.addAttribute("totalMotoristas", consultarMotoristasUseCase.listarTodos().size());
        model.addAttribute("totalAlocacoesAtivas", alocacoesAtivas);
        return "index";
    }
}
