package ghkwhd.itemService.web.basic;

import ghkwhd.itemService.domain.item.Item;
import ghkwhd.itemService.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    // ItemRepository가 자동 주입 (스프링 빈으로 등록되어 있기 때문에)
    // 생성자가 하나만 있는 경우, @Autowired 생략 가능
    // @RequiredArgsConstructor를 사용하면 아래 생성자 코드 생략 가능
        // final 이 붙은 멤버변수만 사용해서 생성자를 자동으로 만들어준다
    /*
    @Autowired
    public BasicItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    */

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @PostConstruct
    // 테스트용 데이터 추가
    // 해당 빈의 의존관계가 모두 주입되고 나면 초기화 용도로 호출된다
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId,
                       Model model) {

        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    @PostMapping("/add")
    public String save() {
        return "basic/addForm";
    }

}
