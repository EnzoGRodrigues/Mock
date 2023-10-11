package org.acme;



import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class TesteDesconto {
    @Mock
    DescontoService descontoService;

//    @InjectMocks
//    Pedido pedido;

    @Test
    public void testarDescontoUm(){
        when(descontoService.calcularDesconto(30.0)).thenReturn(27.0);
        List<ItemPedido> itens = new ArrayList<>();
        itens.add(new ItemPedido(30.0));
        Pedido pedido = new Pedido(itens, descontoService);
        assert pedido.calcularValorTotal() == 27.0;
        log.info("Valor total: " + pedido.calcularValorTotal());
    }

    @Test
    public void testarDescontoDois(){
        when(descontoService.calcularDesconto(30.0)).thenReturn(0.0);
        List<ItemPedido> itens = new ArrayList<>();
        itens.add(new ItemPedido(30.0));
        Pedido pedido = new Pedido(itens, descontoService);
        double valorTotal = pedido.calcularValorTotal();
        Assertions.assertEquals(30.0, valorTotal);
   }

    @Test
    public void testarDescontoTres(){
        when(descontoService.calcularDesconto(30.0)).thenReturn(6.0);
        List<ItemPedido> itens = new ArrayList<>();
        itens.add(new ItemPedido(30.0));
        Pedido pedido = new Pedido(itens, descontoService);
        assert pedido.calcularValorTotal() == 24.0;
        log.info("Valor total: " + pedido.calcularValorTotal());
    }

    @Test
    public void testeQuatro(){
//        DescontoService descontoService = new DescontoService() {
//            @Override
//            public double calcularDesconto(double valorTotal) {
//                return valorTotal * 1.1;
//            }
//        };
        DescontoService mock = org.mockito.Mockito.mock(DescontoService.class);
        List<ItemPedido> itens = new ArrayList<>();
        itens.add(new ItemPedido(30.0));
        Pedido pedido = new Pedido(itens, descontoService);
        when(mock.calcularDesconto(-3)).thenThrow(new IllegalArgumentException());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            mock.calcularDesconto(-3);
        });
        log.info("Valor total: " + pedido.calcularValorTotal());
    }


}
