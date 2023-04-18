//
//  ContentView.swift
//  Gde Halal
//
//  Created by Сергей Лоскутников on 04.03.2023.
//

import SwiftUI
import WebKit

class NotificationScriptMessageHandler: NSObject, WKScriptMessageHandler {
  
    func tinkoffPayIsNotAllowed() {
        //
    }
    
    
    func userContentController(_ userContentController: WKUserContentController, didReceive message: WKScriptMessage) {
        
        print(" in this call ! " + message.name )
        
        if message.name == "CallTinkoff" {
        
           // callTinkoffPayment()
            
           // print(message.body)
            
            
        }
    }
}



struct myWebLosView : UIViewRepresentable {
    
    func makeUIView(context: Context) -> WKWebView {
        let webConfiguration = WKWebViewConfiguration()
      
        let userContentController = WKUserContentController()
        
       // let myNot = NotificationScriptMessageHandler()
        
    
        //userContentController.add( myNot, name: "CallTinkoff")
        //webConfiguration.userContentController = userContentController
        webConfiguration.preferences.javaScriptEnabled = true
        
        
        let webView = WKWebView(frame: .zero, configuration: webConfiguration)
        
          // https://platform.chicaga.ru/app/#/
          //
            let myURL = URL(string:"https://gdehalal.ru/")
            let myRequest = URLRequest(url: myURL!)
        
            webView.load(myRequest)
        
        return webView
    }
    
    
    func userContentController(_ userContentController: WKUserContentController, didReceive message: WKScriptMessage) {
          if message.name == "test", let messageBody = message.body as? String {
              print(messageBody)
          }
      }
    
    
    typealias UIViewType = WKWebView
    
    class Coorddinator : NSObject, WKUIDelegate {
        let parent : myWebLosView
        
        init(_ parent : myWebLosView){
            self.parent = parent
        }
        
    }
    
    
    func updateUIView(_ uiView: UIViewType, context: Context) {
        
    }
    
}


struct ContentView: View {
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        GeometryReader { geometry in
            ZStack(alignment: .topLeading) {
                Rectangle().fill(colorScheme == .dark ? Color.black : Color.white) // change color based on color scheme
                    .frame(height: geometry.safeAreaInsets.top)
                myWebLosView().frame(maxWidth: .infinity, maxHeight: .infinity)
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
