package com.chenaws.schoolDaysGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

// 无背景板
public class SchoolDaysGUI {
    public static void main(String[] args) {
        // 创建主窗口
        JFrame frame = new JFrame("开学周数计算器");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // 居中显示

        // 创建面板
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        frame.add(panel);

        // 输入提示和文本框
        JLabel label = new JLabel("请输入开学时间 (yyyy-MM-dd HH:mm:ss)：");
        JTextField textField = new JTextField("2025-03-01 00:00:00");

        // 输出区域
        JLabel daysLabel = new JLabel("天数：");
        JLabel weeksLabel = new JLabel("周数：");

        // 按钮
        JButton calcButton = new JButton("计算");

        label.setFont(new Font("SansSerif", Font.PLAIN, 25));
        textField.setFont(new Font("SansSerif", Font.PLAIN, 25));
        calcButton.setFont(new Font("SansSerif", Font.BOLD, 25));
        daysLabel.setFont(new Font("SansSerif", Font.PLAIN, 25));
        weeksLabel.setFont(new Font("SansSerif", Font.PLAIN, 25));

        // 按钮点击事件
        calcButton.addActionListener((ActionEvent e) -> {
            String input = textField.getText();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime start = LocalDateTime.parse(input, formatter);
                LocalDateTime now = LocalDateTime.now();

                long days = ChronoUnit.DAYS.between(start, now);
                long weeks = ChronoUnit.WEEKS.between(start, now);

                daysLabel.setText("天数：" + days + " 天");
                weeksLabel.setText("周数：" + weeks + " 周（完整周）");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "日期格式错误，请使用 yyyy-MM-dd HH:mm:ss", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });

        // 添加到面板
        panel.add(label);
        panel.add(textField);
        panel.add(calcButton);
        panel.add(daysLabel);
        panel.add(weeksLabel);

        // 显示窗口
        frame.setVisible(true);
    }
}
