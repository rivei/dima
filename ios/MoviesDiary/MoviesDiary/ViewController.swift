//
//  ViewController.swift
//  MoviesDiary
//
//  Created by Giovanni Quattrocchi on 22/12/17.
//  Copyright © 2017 Giovanni Quattrocchi. All rights reserved.
//

import UIKit


class ViewController: UIViewController {

    @IBOutlet weak var counterLabel: UILabel!
    
    var counter = Counter.instance
    
    override func viewDidLoad() {
        super.viewDidLoad()
        updateCounterLabel()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


    @IBAction func onPlusOneButtonClicked(_ sender: Any) {
        counter.value += 1
        updateCounterLabel()
    }
    
    func updateCounterLabel() {
        counterLabel.text = "\(counter.value)"
    }
}

