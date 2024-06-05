package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {
    @JsonProperty(value = "ID_MENU")
    public Long idMenu;

    @JsonProperty(value = "MENU_BADGE")
    public String menuBadge;

    @JsonProperty(value = "MENU_BADGE_CLASS")
    public String menuBadgeClass;

    @JsonProperty(value = "MENU_ICON")
    public String menuIcon;

    @JsonProperty(value = "MENU_NAME")
    public String menuName;

    @JsonProperty(value = "MENU_ORDER")
    public Long menuOrder;

    @JsonProperty(value = "MENU_PARENT")
    public Long menuParent;

    @JsonProperty(value = "MENU_PRJ_NAME")
    public String menuPrjName;

    @JsonProperty(value = "MENU_ROUTER_LINK")
    public String menuRouterLink;

    @JsonProperty(value = "MENU_TARGET")
    public String menuTarget;

    @JsonProperty(value = "MENU_URL")
    public String menuUrl;

    @JsonProperty(value = "ACTIVE")
    public Boolean active;
}
