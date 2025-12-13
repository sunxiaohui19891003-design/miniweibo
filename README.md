# MiniWeibo（ミニ微博システム）
## 🔗 オンラインデモ（Live Demo）

- フロントエンド：https://miniweibo-frontend.onrender.com
- バックエンド（API）：https://miniweibo-backend.onrender.com

## 📌 プロジェクト概要
MiniWeibo は Spring Boot と MySQL を使って作成したシンプルな微博（SNS）バックエンドシステムです。  
ユーザー登録、ログイン、ログアウト、微博投稿、編集、削除、ユーザー別一覧表示など、基本的な機能を実装しています。

## ⭐ 主な機能
### ● ユーザー機能
- ユーザー登録
- ログイン（Session 使用）
- ログアウト
- ユーザー削除（本人の微博も自動削除）

### ● 微博機能
- 微博投稿（ログイン必須）
- 微博一覧取得
- ユーザー別の微博取得
- 微博編集
- 微博削除

## 🛠 使用技術
- Spring Boot 3
- Spring MVC
- Spring Data JPA
- MySQL
- HttpSession
- Maven

## 📁 フォルダ構成
```
src/
 ├ controller/      コントローラ（API）
 ├ service/         サービス層
 ├ repository/      データアクセス
 ├ entity/          エンティティ（User / Weibo）
 └ exception/       例外処理
```


## 🚀 実行方法
1. MySQL を起動し、データベースを作成
2. `application.properties` に接続情報を設定
3. `mvn spring-boot:run` で起動

## 📌 補足
ログイン後、Session に `userId` を保存し、微博投稿などの操作に利用しています。  
未ログイン状態で投稿すると、エラーメッセージ（「請先登录」）を返します。
