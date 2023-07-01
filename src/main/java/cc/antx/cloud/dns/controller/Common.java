package cc.antx.cloud.dns.controller;

import cc.antx.cloud.dns.entity.Account;
import cc.antx.cloud.dns.mapper.AccountMapper;
import cc.antx.cloud.dns.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Common {
    public static boolean checkLoginStatus(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        if (username == null || password == null || username.isEmpty() || password.isEmpty())
            return false;
        else {
            Account account = AccountMapper.getAccountByUsername(username);
            return StringUtils.getStringSHA512(account.getSalt().concat(account.getPassword()))
                    .equals(StringUtils.getStringSHA512(account.getSalt().concat(password)));
        }
    }
}
