package com.chenaws.schoolDaysGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

// 带背景图片
public class SchoolDaysGUIWithBackground {
    public static void main(String[] args) {
        JFrame frame = new JFrame("开学周数计算器");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // 居中
        System.out.println("当前运行目录：" + System.getProperty("user.dir"));

        // 加载背景图片
        BufferedImage bgImage = null;
        try {
            InputStream is = SchoolDaysGUIWithBackground.class.getResourceAsStream("/static/unico.jpg");    // 这里改背景图片路径
            bgImage = ImageIO.read(is);
        } catch (Exception e) {
            System.out.println("背景图片加载失败：" + e.getMessage());
        }

        // 创建带背景的面板
        BackgroundPanel panel = new BackgroundPanel(bgImage);
        panel.setLayout(new GridLayout(6, 1, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60)); // 内边距
        panel.setOpaque(false); // 关键设置

        // 字体
        Font font = new Font("微软雅黑", Font.BOLD, 22);

        // 组件
        JLabel label = new JLabel("请输入开学时间 (yyyy-MM-dd HH:mm:ss)：");
        label.setFont(font);
        label.setForeground(Color.BLACK);
        label.setOpaque(false); // 设置为透明

        JTextField textField = new JTextField("2025-03-01 00:00:00");
        textField.setFont(font);
        textField.setOpaque(false);
        textField.setBackground(new Color(255, 255, 255, 150)); // 半透明白
        textField.setForeground(Color.BLACK);
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JButton calcButton = new JButton("计算");
        calcButton.setFont(font);
        calcButton.setOpaque(false);
        calcButton.setContentAreaFilled(false);
        calcButton.setForeground(Color.BLACK);
        calcButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JLabel daysLabel = new JLabel("天数：");
        daysLabel.setFont(font);
        daysLabel.setForeground(Color.BLACK);
        daysLabel.setOpaque(false);

        JLabel weeksLabel = new JLabel("周数：");
        weeksLabel.setFont(font);
        weeksLabel.setForeground(Color.BLACK);
        weeksLabel.setOpaque(false);

        // 按钮事件
        calcButton.addActionListener((ActionEvent e) -> {
            String input = textField.getText();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime start = LocalDateTime.parse(input, formatter);
                LocalDateTime now = LocalDateTime.now();

                long days = ChronoUnit.DAYS.between(start, now);
                long weeks = ChronoUnit.WEEKS.between(start, now);

                daysLabel.setText("天数：" + days + " 天");
                weeksLabel.setText("周数：" + weeks + " 周");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "日期格式错误，请使用 yyyy-MM-dd HH:mm:ss", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });

        // 添加组件到面板
        panel.add(label);
        panel.add(textField);
        panel.add(calcButton);
        panel.add(daysLabel);
        panel.add(weeksLabel);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}

// 自定义背景面板类
class BackgroundPanel extends JPanel {
    private final Image image;

    public BackgroundPanel(Image image) {
        this.image = image;
        this.setOpaque(false); // 关键设置
    }

    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        if (image != null) {
//            int panelWidth = getWidth();
//            int panelHeight = getHeight();
//
//            int imgWidth = image.getWidth(null);
//            int imgHeight = image.getHeight(null);
//
//            // 计算缩放比例
//            double widthRatio = (double) panelWidth / imgWidth;
//            double heightRatio = (double) panelHeight / imgHeight;
//            double scale = Math.min(widthRatio, heightRatio); // 保持比例
//
//            int newImgWidth = (int) (imgWidth * scale);
//            int newImgHeight = (int) (imgHeight * scale);
//
//            // 居中显示
//            int x = (panelWidth - newImgWidth) / 2;
//            int y = (panelHeight - newImgHeight) / 2;
//
//            g.drawImage(image, x, y, newImgWidth, newImgHeight, this);
//        }
        super.paintComponent(g);
        if (image != null)
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
