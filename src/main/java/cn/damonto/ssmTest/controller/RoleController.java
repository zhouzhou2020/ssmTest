package cn.damonto.ssmTest.controller;

import cn.damonto.ssmTest.common.AjaxResult;
import cn.damonto.ssmTest.entity.Role;
import cn.damonto.ssmTest.entity.RoleFunction;
import cn.damonto.ssmTest.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/index")
    public String index(){
        return "security/role/role_list";
    }

    @PostMapping("/getRoles")
    public List<Role> getRoles(Integer page, Integer rows){
        return roleService.getRoles(page, rows);
    }

    @PostMapping("/addEditRole")
    @ResponseBody
    public AjaxResult addEditRole(Role role){

        String functionIds = role.getFunctionIds();
        String[] idArray = functionIds.split(",");
        List<RoleFunction> roleFunctions = new ArrayList<RoleFunction>();
        for(int i=0;i<idArray.length;i++){
            RoleFunction rf = new RoleFunction();
            rf.setFunctionId(Long.valueOf(idArray[i]));
            rf.setStatus(1);
            roleFunctions.add(rf);
        }
        if(null == role.getId()){
            roleService.addRole(role, roleFunctions);
        }else{
            roleService.editRole(role, roleFunctions);
        }
        return AjaxResult.success();
    }

    @PostMapping("/deleteRole")
    @ResponseBody
    public AjaxResult deleteRole(Long id){
        roleService.deleteRole(id);
        return AjaxResult.success();
    }
}
